package com.pior.filme.texo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
