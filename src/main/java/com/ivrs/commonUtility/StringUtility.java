package com.ivrs.commonUtility;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * @purpose Check String Methods from StringUtility
 * @author jayesh.gautam
 * @date 08-Jan-2025
 */
@Slf4j
public class StringUtility {

    // Define the patterns
    private static final String[] DATE_PATTERNS = {
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
            "yyyy-MM-dd'T'HH:mm",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss.SSS",
            "yyyy-MM-dd HH:mm:ss.SS",
            "yyyy-MM-dd HH:mm:ss.S",
            "yyyy-MM-dd",
            "dd-MMM-yyyy",
            "dd MMM yyyy",
            "yyyy/MM/dd",
            "yyy MM dd",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
            "dd-MMM-yyyy HH:mm:ss",
            "dd MMM yyyy HH:mm:ss",
            "dd MMM yyyy HH:mm",
            "yyyy-MM",
            "yyyy-MMM",
            "yyyy/MM"
    };

    // Desired output pattern
    private static final String OUTPUT_PATTERN = "dd-MM-yyyy";

    public static boolean isNullOrEmptyString(String input) {
        return null == input || input.trim().isEmpty();
    }

    public static String getTrimmedString(String input) {
            String trimmedStr = null;
            if (!isNullOrEmptyString(input)) {
                trimmedStr = input.trim();
            }
        return trimmedStr;
    }

    public static String generateRandomId(int length) {
        String randomString = UUID.randomUUID().toString();
        if (length > randomString.length()) {
            length = randomString.length();
        }
        return (length > 0) ? randomString.substring(0, length) : randomString;
    }

    public static String formatDateString(String inputDate) {
        for (String pattern : DATE_PATTERNS) {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat(pattern);
                Date date = inputFormat.parse(inputDate);
                return new SimpleDateFormat(OUTPUT_PATTERN).format(date);
            } catch (ParseException e) {
                // Skip to the next pattern if parsing fails
            }
        }
        return null;
    }


    public static String generateRandomIntegers(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        int lowerBound = (int) Math.pow(10, size - 1);
        int upperBound = (int) Math.pow(10, size) - 1;

        int randomNumber = lowerBound + (int) (Math.random() * (upperBound - lowerBound + 1));
        return String.valueOf(randomNumber);
    }


    /**
     * Converts a date string from one format to another.
     *
     * @param inputDate The date string to be converted.
     * @param outputFormat The format to convert the input date string to.
     * @return The formatted date string.
     * @throws Exception If there's an issue during parsing or formatting.
     */
    public static String convertDateFormat(String inputDate, String outputFormat) throws Exception {
        Date parsedDate = null;
        inputDate = inputDate.trim();
        for (String format : DATE_PATTERNS) {
            try {
                SimpleDateFormat inputFormatter = new SimpleDateFormat(format);
                inputFormatter.setLenient(false); // Ensures strict date parsing
                 parsedDate = inputFormatter.parse(inputDate);
                System.out.println("Parsed using format: " + format);
                SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat);
                return outputFormatter.format(parsedDate);
            } catch (Exception e) {
                // Skip to the next pattern if parsing fails
            }
        }
        return "";
    }


    public static String convertDateFormatForAdminLog(LocalDateTime createdOnDate) {
        String formattedDate = "";
        try {
            LocalDateTime createdOn = LocalDateTime.parse(createdOnDate.toString());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
            formattedDate = createdOn.format(formatter);
        } catch (Exception e) {
        }
        return formattedDate;
    }
}
