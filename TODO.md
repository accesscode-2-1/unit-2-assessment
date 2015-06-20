
1. find out what tests to write
    1. ListView
        - tiles using these colors, looping 500 times only:

            // from lonelyplanet
            #142b44
            #1d508d
            #297cbb
            #288ad6
            #0fdebd
            #16c98d
            #feef6d
            #ffc83f
            #fa5e5b
            #bf538d

        - ListView exists
        - ListView has certain layout properties
        - when clicked, it should invert its color
    2. ArrayAdapters
        - Override Object.toString()
    3. Network call
        - Maybe some sort of timed test (ensure things are off UI thread)
    4. API use (flickr API again?)
        - when API is up
        - when API is delayed over NetworkTimeout (but not down)
        - when API is down
        - API information has been retrieved (use metadata?)
        - API information has been parsed correctly (JSON)
    5. flat file like JSON
        - store to file
        - serialize data correctly
        - retrieve from file
    6. notifications
        - simple
        - updating the same notification (instead of creating new ones)
        - with photo
        - with buttons
    7. Extend one of the existing Views
        - probably an extension of ListView that includes indexing or toasting when element is clicked
