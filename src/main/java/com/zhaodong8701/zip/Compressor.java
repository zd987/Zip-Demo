package com.zhaodong8701.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compressor {
	boolean recurse = false;

	public boolean isRecurse() {
		return recurse;
	}

	public void setRecurse(boolean recurse) {
		this.recurse = recurse;
	}

	private void zip(ZipOutputStream out, String filePath, byte[] buffer)
			throws Exception {
		File f = new File(filePath);
		String base = f.getPath();
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (!this.recurse | fl.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/"));
				System.out.println("adding: " + base + "/");
			} else {
				for (int i = 0; i < fl.length; i++) {
					zip(out, base + "/" + fl[i].getName(), buffer);
				}
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			System.out.println("adding: " + base);
			FileInputStream in = new FileInputStream(f);
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			out.closeEntry();
			in.close();
		}
	}

	public void compress(String zipFilePath, List<String> files) {
		ZipOutputStream out;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipFilePath));
		} catch (FileNotFoundException e) {
			System.err.println("Exception occurs with the input zip file: "
					+ zipFilePath);
			e.printStackTrace();
			return;
		}

		List<String> processFileList = new ArrayList<String>();
		for (String filePath : files) {
			List<String> matchedFiles = new ArrayList<String>();
			if (filePath.contains("*") || filePath.contains("?")) {
				matchedFiles.addAll(Utils.parseWildcardFile(filePath));
			} else {
				File file = new File(filePath);
				if (file.exists()) {
					matchedFiles.add(filePath);
				}
			}
			if (matchedFiles.size() == 0) {
				System.out.println("	zip warning: name not matched: "
						+ filePath);
			} else {
				processFileList.addAll(matchedFiles);
			}
		}

		byte[] buffer = new byte[1024];
		for (String filePath : processFileList) {
			try {
				zip(out, filePath, buffer);
			} catch (Exception e) {
				System.err.println("Exception occurs with the input file "
						+ filePath);
				e.printStackTrace();
				break;
			}
		}
		try {
			out.close();
		} catch (IOException e) {
			System.err
					.println("Exception occurs when closing the input zip file: "
							+ zipFilePath);
			e.printStackTrace();
			return;
		}
	}
}
