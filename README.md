# TDD-Code-Test
You are required to write a program which filters application request log extracts by a number of different properties.

A log extract file contains a header line, followed by zero or more data lines, in comma-separated value format. The
first column is the Unix timestamp of the time the request was made, the second column is the country from which the
request originated, and the third column is the time in milliseconds which the request took to complete. The data lines
are not guaranteed to be in any particular order.

An example file is:

    REQUEST_TIMESTAMP,COUNTRY_CODE,RESPONSE_TIME
    1433190845,US,539
    1432917066,GB,37

The features which you must implement have been prototyped in the class uk.sky.DataFilterer. You must implement the
features in this class without changing the signatures of any methods or add any new dependencies. You must also provide
evidence that the features you have implemented work correctly.

Guide time is 1-2 hours. Your solution will be judged on a number of criteria pertinent to good software development practice. Incomplete solutions are acceptable.
