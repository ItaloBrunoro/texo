package com.pior.filme.texo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * FilesUtil.
 */
public class FilesUtil {

  private FilesUtil() {
  }

  public static List<String> readLinesFromFile(final InputStream file) {

    BufferedReader br;
    List<String> result = new ArrayList<>();

    try {
      String line;
      br = new BufferedReader(new InputStreamReader(file));
      while ((line = br.readLine()) != null) {
        result.add(line);
      }

    } catch (IOException e) {
      return null;
    }

    return result;
  }
}
