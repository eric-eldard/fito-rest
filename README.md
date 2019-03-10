# fito-rest

## About
[Fitocracy.com](https://www.fitocracy.com) is the first resource to ever help me build and _stick with_ a good workout
regimen and for that I'm really appreciative.  But it's no secret that the site has seen better days.  The new ownership
seems far more concerned with selling online coaching than with maintaining the original core product.  This isn't a
surprise, as the original creators got out in 2016 after years of struggling to monetize through premium memberships and
sparse ads.

The site has seen no new feature development in years.  When the prop system broke in January 2019, and remained broken
for several days, I realized it was time to start doomsday prepping for the eventual dereliction and/or shutdown of the
site. While I've really enjoyed both the community and the gamification, the thing I can't lose is my fitness log.

There are (at least) two Fitocracy REST APIs, both of which are currently in use on their website.  The v1 API seems to
be geared towards aggregating a user's activities across their time on Fito, like on the site's Performance page,
whereas the v2 API can be used to pull back individual workouts for a user.  The v2 API also offers access to exercise
descriptions and metrics.  While they're backed by the same data, they are modeled completely differently.

This project was thrown together to pull a Fitocracy user's full exercise log from a combination of Fito's REST API (v1
and v2) and some page scraping, both for posterity and for playing around with the data.  Fork away—I'd love to
collaborate and see what you do with it!

## Build
`mvn clean package`

## Use
Start with the tools in **fito-client** to retrieve data directly from Fitocracy, then manipulate and record that data
with **fito-file**.  See **fito-sample** for working examples.