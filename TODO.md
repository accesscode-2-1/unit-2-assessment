
1. find out what tests to write
    1. [ ] ListView (@shurane)
        - [x] tiles using these colors, looping x times only:

            ```
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
            ```

        - [x] ListView has certain layout properties
            - [x] height of row should be configurable
        - [ ] when clicked, it should invert its color
        - [x] create it in 2 stages
            - [x] first level elements first
            - [x] second level elements afterwards
    2. [x] write a custom Adapter that extends `ArrayAdapter/BaseAdapter` (@shurane)
    3. Network call (@amyquispe)
        - Maybe some sort of timed test (ensure things are off UI thread)
        - http://www.mdswanson.com/blog/2013/12/16/reliable-android-http-testing-with-retrofit-and-mockito.html
        - HTTP GET request we'll be using: `https://httpbin.org/get?custname=james+dean&custtel=347-841-6090&custemail=hello%40c4q.nyc&size=small&topping=cheese&delivery=18%3A15&comments=Leave+it+by+the+garage+door.+Don%27t+ask+any+questions.`
        - HTTP POST request we'll be using:

            ```
            curl -X POST \
                -d "custname=james dean" \
                -d "custtel=347-8431-6090" \
                -d "custemail=hello@c4q.nyc" \
                -d "size=small" \
                -d "topping=cheese" \
                -d "delivery=22:15" \
                -d "comments=Leave it by the garage door. Don't ask any questions." \
                https://httpbin.org/post
            ```

    4. API use (flickr API again?) (@amyquispe)
        - when API is up
        - when API is delayed over NetworkTimeout (but not down)
        - when API is down
        - API information has been retrieved (use metadata?)
        - API information has been parsed correctly (JSON)
    5. flat file like JSON (@amyquispe)
        - store to file
        - serialize data correctly
        - retrieve from file
    6. notifications (@amyquispe)
        - simple
        - updating the same notification (instead of creating new ones)
        - with photo
        - with buttons
    7. Extend one of the existing Views for Inheritance (@shurane)
        - probably an extension of ListView that includes indexing or toasting when element is clicked
        - or a variant of a ListView that takes a `(List<Color> colors, int length)` and provides `length` tiles, just like in #1

- use travis-ci to generate and collect test reports
