package com.hetzer.crawlite.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.Loader;
import javassist.NotFoundException;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.mock.MockProcessor;

public class CrawlJobFactory {
	private static final String PROCESSOR_OBJECT = "ProcessorObject";
	private static final HashMap<String, Class<?>> clazzMap = new HashMap<String, Class<?>>();
	private String jobsDir;
	private String jobName = "";
	private File config;
	private Map<String, Object> map;

	public CrawlJobFactory(Map<String, Object> map) {
		this.map = map;
		if (map != null && map.containsKey("name")) {
			jobName = (String) map.get("name");
		} else {
			jobName = "default-" + (new Random().nextInt() & 0x7FFFFFFF);
		}

	}

	public CrawlJob makeJob(CrawlJobManager cjm, File configFile) {
		jobsDir = cjm.getJobPath();
		config = configFile;

		CrawlJob job = new CrawlJob(cjm);
		try {
			loadConfigs(job);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		job.setDirectory(new File(jobsDir));
		job.setName(jobName);
		job.setSeeds(new String[] { "a", "b", "c", "d" });
		return job;
	}

	public CrawlJob makeDefaultJob(CrawlJobManager cjm) {
		jobsDir = cjm.getJobPath();
		File dirFile = makedir(jobsDir);
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("CrawlJob");
		root.addAttribute("name", jobName);
		root.addAttribute("class", CrawlJob.class.getName());

		Element processors = root.addElement("Processor");
		for (int i = 0; i < 2; i++) {
			Element p = processors.addElement(PROCESSOR_OBJECT);
			p.addAttribute("name", "MockProcess");
			p.addAttribute("class", MockProcessor.class.getName());
		}
		Element p = processors.addElement(PROCESSOR_OBJECT);
		p.addAttribute("name", "TestP");
		p.addAttribute("file", "TestP.class");

		File config = null;
		try {
			document.normalize();
			config = new File(dirFile, "config.xml");
			XMLWriter output = new XMLWriter(new FileWriter(config),
					OutputFormat.createPrettyPrint());
			output.write(document);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return makeJob(cjm, config);
	}

	private void loadConfigs(CrawlJob job) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(config);
		Element root = document.getRootElement();

		jobName = root.attributeValue("name");

		Element threadNumElement = root.element("ThreadNum");
		job.setThreadNum(new Integer(threadNumElement.getText()));

		Element retryTimesElement = root.element("RetryTimes");
		job.setRetryTimes(new Integer(retryTimesElement.getText()));
		
		Element processorsElement = root.element("Processor");
		Iterator iterator = processorsElement.elementIterator();
		for (; iterator.hasNext();) {
			Element element = (Element) iterator.next();
			if (element.getName().equals(PROCESSOR_OBJECT)) {
				Class<? extends Processor> clazz = null;
				String filePath = null;
				if (null != (filePath = element.attributeValue("file"))) {
					try {
						String className = element.attributeValue("name");
						if (clazzMap.containsKey(filePath)) {
							clazz = (Class<? extends Processor>) clazzMap
									.get(filePath);
						} else {
							FileInputStream ins = new FileInputStream(filePath);
							CtClass ctClass = ClassPool.getDefault()
									.makeClassIfNew(ins);

							clazz = ctClass.toClass();
							clazzMap.put(filePath, clazz);
						}
					} catch (IOException | RuntimeException
							| CannotCompileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					try {
						clazz = (Class<? extends Processor>) Class
								.forName(element.attributeValue("class"));

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				try {
					Processor processor = (Processor) clazz.newInstance();
					job.addProcessor(processor);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public File makeConfigXml(File dirFile) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("CrawlJob");

		root.addAttribute("name", (String) map.get("name"));
		root.addAttribute("class", CrawlJob.class.getName());

		Element threadConfig = root.addElement("ThreadNum");
		threadConfig.addAttribute("name", "ThreadNum");
		threadConfig.setText(Integer.toString((int) map.get("ThreadNum")));
		
		Element retryTimes = root.addElement("RetryTimes");
		retryTimes.addAttribute("name", "ThreadNum");
		retryTimes.setText(Integer.toString((int) map.get("RetryTimes")));

		Element processors = root.addElement("Processor");
		List<Class<? extends Processor>> processorList = (List<Class<? extends Processor>>) map
				.get("processorList");
		if (processorList != null) {
			for (Iterator iterator = processorList.iterator(); iterator
					.hasNext();) {
				Class<? extends Processor> clazz = (Class<? extends Processor>) iterator
						.next();
				Element p = processors.addElement(PROCESSOR_OBJECT);
				p.addAttribute("name", clazz.getSimpleName());
				p.addAttribute("class", clazz.getName());
			}
		}
		File config = null;
		try {
			document.normalize();
			config = new File(dirFile, "config.xml");
			XMLWriter output = new XMLWriter(new FileWriter(config),
					OutputFormat.createPrettyPrint());
			output.write(document);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return config;
	}

	public File makedir(String jobsDir) {
		this.jobsDir = jobsDir;
		File dir = new File(jobsDir);
		dir.mkdir();
		File theJob = new File(dir, jobName);
		theJob.mkdir();

		return theJob;
	}
}
