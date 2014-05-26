package com.hetzer.crawlite.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.Loader;
import javassist.NotFoundException;

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

	
	public CrawlJob makeJob(CrawlJobManager cjm, File configFile) {
		jobsDir = cjm.getJobPath();
		config = configFile;

		CrawlJob job = new CrawlJob(cjm);
		try {
			loadConfigs(job);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		job.setName(jobName);
		job.setSeeds(new String[] { "a", "b", "c", "d" });
		return job;
	}

	public CrawlJob makeJob(CrawlJobManager cjm) {
		jobName = "default-" + (new Random().nextInt() & 0x7FFFFFFF);
		jobsDir = cjm.getJobPath();
		File dirFile = makedir();
		config = makeConfigXml(dirFile);

		return makeJob(cjm, config);
	}

	private void loadConfigs(CrawlJob job) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(config);
		Element root = document.getRootElement();
		jobName = root.attributeValue("name");
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

	private File makeConfigXml(File dirFile) {
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
		return config;
	}

	private File makedir() {
		File dir = new File(jobsDir);
		dir.mkdir();
		File theJob = new File(dir, jobName);
		theJob.mkdir();

		return theJob;
	}
}
