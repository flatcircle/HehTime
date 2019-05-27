package io.flatcircle.hehtime

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.Locale

/**
 * Created by jacquessmuts on 2019-05-27
 * Basic entry point for handling time things
 */
@Suppress("unused")
object HehTime {

    private val DateFormatPatterns = ArrayList<String>()

    /**
     * Add a string pattern that should be interpreted by Heh into a Date() object, using [parseStringIntoDate]
     *
     * @param pattern a string pattern, like "yyyy-MM-dd'T'HH:mm:ss.SSSZ" or "yyyy-MM-dd'T'HH:mm:ssZ"
     * @see [SimpleDateFormat]
     */
    fun addDateFormat(pattern: String) {

        if (DateFormatPatterns.contains(pattern))
            return

        DateFormatPatterns.add(pattern)
    }

    private fun getDateFormats(): ArrayList<SimpleDateFormat> {
        val toReturn = ArrayList<SimpleDateFormat>()

        DateFormatPatterns.forEach { pattern ->
            toReturn.add(SimpleDateFormat(pattern, Locale.US))
        }
        return toReturn
    }

    /**
     * Returns unix time in millis
     */
    fun currentTime(): Long {
        return System.currentTimeMillis()
    }

    /**
     * Parses a given string into a [Date] object. Requires that you add accepted patterns beforehand,
     * using [addDateFormat]. Returns a new Date Object if none of the patterns match.
     *
     * @param
     */
    fun parseStringIntoDate(dateTimeString: String): Date {

        if (DateFormatPatterns.isNullOrEmpty())
            throw IllegalStateException("You must set the date pattern beforehand using addDateFormat")

        getDateFormats().forEach { simpleDateFormat ->
            try {
                return simpleDateFormat.parse(dateTimeString)
            } catch (e: ParseException) {
                // TODO: add logger/interceptor here?
            }
        }
        // error?.let { throw it }
        return Date()
    }

    /**
     * Takes a given date and formats it to a string using either the first preset pattern, or with
     * the given pattern
     */
    fun formatToString(timeInMillis: Long, pattern: String? = null): String {
        return formatToString(Date(timeInMillis), pattern)
    }
    /**
     * Takes a given date and formats it to a string using either the first preset pattern, or with
     * the given pattern
     */
    fun formatToString(date: Date, pattern: String? = null): String {

        if (pattern != null) {
            return SimpleDateFormat(pattern, Locale.US).format(date)
        } else if (DateFormatPatterns.isNotEmpty()) {
            return getDateFormats().first().format(date)
        } else {
            throw IllegalStateException("You must set the date pattern beforehand using addDateFormat")
        }
    }
}

/**
 * Commonly used date/time formats for parsing, from [ISO8601Format](http://cdsportal.u-strasbg.fr/taptuto/javadoc/uws/ISO8601Format.html)
 */
sealed class CommonDateFormat(val pattern: String) {
    object Year : CommonDateFormat("YYYY")
    object YearMonth : CommonDateFormat("YYYY-MM")
    object Date : CommonDateFormat("YYYY-MM-DD")
    object DateMinute : CommonDateFormat("YYYY-MM-DD'T'hh:mmTZD")
    object DateSecond : CommonDateFormat("YYYY-MM-DD'T'hh:mm:ssTZD")
    object DateMillisecond : CommonDateFormat("YYYY-MM-DD'T'hh:mm:ss.sTZD")
}

/**
 * Commonly used time-elapsed units for calculating absolute-time differences.
 */
sealed class TimeUnit(val milliseconds: Long) {
    object Second : TimeUnit(1000L)
    object Minute : TimeUnit(60000L)
    object Hour : TimeUnit(3600000L)
    object Day : TimeUnit(86400000L)
    object Week : TimeUnit(604800000L)
}