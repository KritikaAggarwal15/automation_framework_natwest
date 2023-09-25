package com.natWest.genericUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class JsonUtility {
    private static ObjectMapper OBJ_MAPPER = new ObjectMapper();

    public static String toJson(Object obj) throws JsonParseException {
        try {
            if (obj == null) {

            }

            return OBJ_MAPPER.writeValueAsString(obj);

        } catch (Exception e) {

            return null;
        }
    }

    /**
     * Method will be used to get the output json response file path
     *
     * @param fileLocation
     * @return String
     */
    public static String getPath(String fileLocation) {
        String dir = System.getProperty("user.dir");
        List pathParts = Arrays.asList(dir, "src", "test", "resources", "jsonRequest", fileLocation);
        String actualPath = StringUtils.join(pathParts, File.separator);

        return actualPath;
    }

    /**
     * Returns file content after reading a json file
     *
     * @param filePath
     * @return String
     */
    public static String readJsonFile(final String filePath) {
        String fileContent = null;
        String completeFilePath = getPath(filePath);
        try {
            fileContent = FileUtils.readFileToString(new File(completeFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

}
 
