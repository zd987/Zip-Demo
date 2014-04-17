package com.zhaodong8701.zip;

import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {
	private Options options;
	private Compressor compressor;

	private void init() {
		options = new Options();
		Option help = new Option("help", "print this message");
		Option recurse = new Option("r", "recurse into directories");

		options.addOption(help);
		options.addOption(recurse);

		compressor = new Compressor();
	}

	public App() {
		init();
	}

	@SuppressWarnings("unchecked")
	private void process(String[] args) {
		CommandLineParser parser = new BasicParser();
		CommandLine cmd;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.err.println("oops, zip is crashed when parsing args.");
			e.printStackTrace();
			return;
		}
		List<String> argList = cmd.getArgList();
		if (argList.size() < 2) {
			pringUsage();
			return;
		}
		if (cmd.hasOption("r")) {
			compressor.setRecurse(true);
		}
		String zipFilePath = argList.get(0);
		List<String> files = argList.subList(1, argList.size());
		compressor.compress(zipFilePath, files);
		System.out.println("Task Complete.");
	}

	private void pringUsage() {
		HelpFormatter formatter = new HelpFormatter();
		String usage = "zip [-options] [zipfile [file ...]]";
		formatter.printHelp(usage, options);
	}

	public static void main(String[] args) throws Exception {
		App app = new App();
		app.process(args);
	}
}
