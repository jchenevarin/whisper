"use strict";

// simple script to import text formatted city list
var fs = require('fs');
var _ = require('lodash')
var jf = require('jsonfile')

// geonameid         : integer id of record in geonames database
// name              : name of geographical point (utf8) varchar(200)
// asciiname         : name of geographical point in plain ascii characters, varchar(200)
// alternatenames    : alternatenames, comma separated, ascii names automatically transliterated, convenience attribute from alternatename table, varchar(10000)
// latitude          : latitude in decimal degrees (wgs84)
// longitude         : longitude in decimal degrees (wgs84)
// feature class     : see http://www.geonames.org/export/codes.html, char(1)
// feature code      : see http://www.geonames.org/export/codes.html, varchar(10)
// country code      : ISO-3166 2-letter country code, 2 characters
// cc2               : alternate country codes, comma separated, ISO-3166 2-letter country code, 200 characters
// admin1 code       : fipscode (subject to change to iso code), see exceptions below, see file admin1Codes.txt for display names of this code; varchar(20)
// admin2 code       : code for the second administrative division, a county in the US, see file admin2Codes.txt; varchar(80)
// admin3 code       : code for third level administrative division, varchar(20)
// admin4 code       : code for fourth level administrative division, varchar(20)
// population        : bigint (8 byte int)
// elevation         : in meters, integer
// dem               : digital elevation model, srtm3 or gtopo30, average elevation of 3''x3'' (ca 90mx90m) or 30''x30'' (ca 900mx900m) area in meters, integer. srtm processed by cgiar/ciat.
// timezone          : the timezone id (see file timeZone.txt) varchar(40)
// modification date : date of last modification in yyyy-MM-dd format

var fields = [
    'geonameid',
    'name',
    'asciiname',
    'alternatenames',
    'latitude',
    'longitude',
    'featureClass',
    'featureCode',
    'countryCode',
    'cc2',
    'adminCode1',
    'adminCode2',
    'adminCode3',
    'adminCode4',
    'population',
    'elevation',
    'dem',
    'timezone',
    'modification'
    ];

// reading source
var lineReader = require('readline').createInterface({
  input: fs.createReadStream('./cities/CA/CA.txt')
});

// writing output
let target = [];
let count = 0;
lineReader.on('line', function (line) {

    let tmp = {};
    count++;
    _.split(line,'\t').forEach((val,i) => {

        if ([1,4,5,8].indexOf(i) != -1) {
            tmp[fields[i]] = val;
        }
    });
    target.push(tmp);
});

lineReader.on('close', function (line) {

    console.log(count + ' cities processed');
    jf.writeFile('./cities/CA.json', target, function (err) {
        if (err) console.error(err);
        else ('over and out!')
    })
});


