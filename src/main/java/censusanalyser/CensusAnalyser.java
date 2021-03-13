package censusanalyser;

import com.opencsv.*;
import com.opencsv.bean.*;
import com.opencsv.exceptions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CensusAnalyser {
    Scanner sc = new Scanner(System.in);
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_TYPE_FILE_PATH = "./src/main/resources/IndiaStateCensusData.txt";

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            if (!csvFilePath.contains(".csv")) {
                throwException();
            }
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CSVReader readerCsv = new CSVReader(new FileReader(String.valueOf(csvFilePath)));
            String[] nextLine;
            nextLine = readerCsv.readNext();
            if (!(nextLine[0].equals("State") && nextLine[1].equals("Population") &&
                    nextLine[2].equals("AreaInSqKm") && nextLine[3].equals("DensityPerSqKm"))) {
                throw new CensusAnalyserException();
            }
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
            int namOfEateries = 0;
            while (censusCSVIterator.hasNext()) {
                namOfEateries++;
                IndiaCensusCSV censusData = censusCSVIterator.next();
            }
            return namOfEateries;

        } catch (IllegalArgumentException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_HEADER);
        }
    }

    public void throwException() {
        throw new IllegalArgumentException();
    }
}
