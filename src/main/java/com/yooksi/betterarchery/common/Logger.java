package com.yooksi.betterarchery.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;

/**
 * A custom wrapper for the log4j Logger. It also has an implementation <br>
 * for creating and reading from a custom secondary mod log file. <p>
 * 
 * <i>Whenever you log an event (except INFO) using this logger, <br>
 * a copy of that log will be stored in a custom mod log file. </i><p>
 * 
 * <i>Thanks to Choonster for writing the original wrapper.</i> 
 */
public abstract class Logger 
{
	private static org.apache.logging.log4j.Logger logger;
	
	static final java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("[dd/MM/yyyy][HH:mm:ss]");
    public static final String modLogFileName = "better-archery-reborn-logger.log";
    public static final String modLogFileDirName = "logs";
	    
    /** A string representation of the mod log Path, here for convenience. */
	private static String modLogFilePath;
	/** A file instance of the mod log file. Used to access file properties. */
	private static File modLogFile;
	
	/** Any event level that has a value below this will not be logged. */
	private static Level threshold = Level.DEBUG;
	
	
	private static void log(Level level, String format, Object... data) 
	{
		logger.printf(level, format, data);
	}

	private static void log(Level level, Throwable throwable, String format, Object... data) 
	{
		logger.log(level, String.format(format, data), throwable);
	}

	private static void log(Level level, Marker marker, String format, Object... data) 
	{
		logger.printf(level, marker, format, data);
	}

	private static void log(Level level, Marker marker, Throwable throwable, String format, Object... data)
	{
		logger.log(level, marker, String.format(format, data), throwable);
	}

	public static void fatal(String format, Object... data) 
	{
		log(Level.FATAL, format, data);
		writeToModLogFile(Level.FATAL, format, null);
	}

	public static void fatal(Marker marker, String format, Object... data) 
	{
		log(Level.FATAL, marker, format, data);
	}

	public static void fatal(Throwable throwable, String format, Object... data) 
	{
		log(Level.FATAL, throwable, format, data);
	}

	public static void fatal(Marker marker, Throwable throwable, String format, Object... data) 
	{
		log(Level.FATAL, marker, throwable, format, data);
	}

	public static void error(String format, Object... data) 
	{
		log(Level.ERROR, format, data);
		writeToModLogFile(Level.ERROR, format, null);
	}

	public static void error(Marker marker, String format, Object... data) 
	{
		log(Level.ERROR, marker, format, data);
	}

	public static void error(Throwable throwable, String format, Object... data) 
	{
		log(Level.ERROR, throwable, format, data);
		writeToModLogFile(Level.ERROR, format, throwable);
	}

	public static void error(Marker marker, Throwable throwable, String format, Object... data) 
	{
		log(Level.ERROR, marker, throwable, format, data);
	}

	public static void warn(String format, Object... data) 
	{
		log(Level.WARN, format, data);
		writeToModLogFile(Level.WARN, format, null);
	}

	public static void warn(Marker marker, String format, Object... data) 
	{
		log(Level.WARN, marker, format, data);
	}

	public static void info(String format, Object... data) 
	{
		log(Level.INFO, format, data);
	}

	public static void info(Marker marker, String format, Object... data) 
	{
		log(Level.INFO, marker, format, data);
	}

	public static void info(Throwable throwable, String format, Object... data) 
	{
		log(Level.INFO, throwable, format, data);
	}

	public static void info(Marker marker, Throwable throwable, String format, Object... data)
	{
		log(Level.INFO, marker, throwable, format, data);
	}

	public static void debug(String format, Object... data) 
	{
		log(Level.DEBUG, format, data);
		writeToModLogFile(Level.DEBUG, format, null);
	}

	public static void debug(Marker marker, String format, Object... data) 
	{
		log(Level.DEBUG, marker, format, data);
	}

	public static void debug(Marker marker, Throwable throwable, String format, Object... data) 
	{
		log(Level.DEBUG, marker, throwable, format, data);
	}

	public static void setLogger(org.apache.logging.log4j.Logger logger) 
	{
		if (Logger.logger != null) 
			throw new IllegalStateException("Attempt to replace logger");

		Logger.logger = logger;
	}
	
	/** Sets the maximum event level that will be logged in the secondary mod log file. */
	public static void setLevel(Level level)
	{
		threshold = level;
	}
	
	/**
	 * Create a log file for this mod, if one doesn't already exist.
	 * 
	 * @author yooksi
	 * @throws IOException only if a serious I/O error occurs (no exception if files already exist). 
	 */
	public static void createModLogFile() throws IOException
	{	
		Path modLogFileDir = null;
		Path pModLogFilePath = null;
		
		try
		{
			modLogFileDir = Paths.get(modLogFileDirName);
			pModLogFilePath = Paths.get(modLogFileDirName, modLogFileName);
			modLogFilePath = pModLogFilePath.toString();
		}
		catch (java.nio.file.InvalidPathException e) 
		{
			log(Level.ERROR, e.fillInStackTrace(), "Unable to create modLogFile.");
			return;  // Files.exists will throw a NPE if we don't.
		}
		
		if (!Files.exists(modLogFileDir))
			Files.createDirectory(modLogFileDir);
		
		if (!Files.exists(pModLogFilePath))
 		    Files.createFile(pModLogFilePath);
		
		modLogFile = new File(modLogFilePath);
		modLogFile.setWritable(true);
	}
	
	/** 
	 * Write a line of text to out custom log file.
	 * 
	 * @author yooksi
	 * @param level the logger event level (used to print it's name)
	 * @param message the String line we want to print to the file
	 */
	private static void writeToModLogFile(Level level, String message, Throwable throwable)
	{
		if (!modLogFile.exists() || level.intLevel() > threshold.intLevel())
			return;

		java.io.BufferedReader buffReader = null;
		java.io.FileWriter fileWriter = null;
		java.io.BufferedWriter buffWriter = null;
		java.io.PrintWriter printWriter = null;
		
		try  // The FileWriter is the only element that can throw an exception here.
		{
			String timeStamp = dateFormat.format(java.util.Calendar.getInstance().getTime());
			
			buffReader = new java.io.BufferedReader(new java.io.FileReader(modLogFilePath.toString())); 
			fileWriter = new java.io.FileWriter(modLogFilePath.toString(), true);
		    buffWriter= new java.io.BufferedWriter(fileWriter);
	        printWriter = new java.io.PrintWriter(buffWriter);

	        String throwableName = throwable != null ? " " + throwable.getClass().getSimpleName() + ":" : "";
	        
	        // Write a new line only if the file is NOT empty.
	        if (buffReader != null && buffReader.readLine() != null)  
	        	buffWriter.newLine(); 
	        
	        printWriter.printf("%s [%s]%s %s", timeStamp, level.name(), throwableName, message).flush();
		}
		catch (IOException e)
		{
			if (!modLogFile.canWrite())
				log(Level.ERROR, "Unable to write to modLogFile, the file is marked as read-only.");
			
			else log(Level.ERROR, e.fillInStackTrace(), "Unable to write to modLogFile, reason unknown.");
		}
		finally  // Be sure to flush and close the open streams.
		{
			if (printWriter != null) 
				printWriter.close(); 
			
			try { if (buffReader != null) buffReader.close(); }	
			catch (IOException e) { log(Level.FATAL, e.fillInStackTrace(), "Unable to close BufferedReader."); }
			
			try { if (fileWriter != null) fileWriter.close(); }	
			catch (IOException e) { log(Level.FATAL, e.fillInStackTrace(), "Unable to close FileWriter."); }
			
			try { if (buffWriter != null) buffWriter.close(); }
			catch (IOException e) { log(Level.FATAL, e.fillInStackTrace(), "Unable to close BufferedWriter."); }
		}
	}
}