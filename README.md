# Panorama

This is a demo application. The application allows data retrieval from Haalcentraal BRP and the Zaken API.

The application is based on modules, where each module is a separate external service.
Each module is stand-alone and provide its own APIs to retrieve information from the external service

## Start Panorama

Start up the docker containers by using `docker compose up -d`. Then start the application by using gradle bootRun.

### Open zaak configuration - localhost

The open zaak database will automatically be filled with several zaken where the BSN numbers match 
those known by the HaalCentraal BRP demo. 
The following BSN numbers are configured to zaken:
 * 999990111 - 2 zaken
 * 999990755 - 4 zaken
 * 999993653 - 4 zaken


# How the application works

Every API call requires aan X-API-KEY, based on the api key certain modules and endpoinnts are or are not available.
When an endpoint is called that is not allowed based on the api key a http 403 (Forbidden) is returned.

## Current endpoints

The demo project contains the following endpoints:
* GET /api/v1/authorization/modules
  * Returns all the modules and endpoints allowed
* GET /api/v1/profile/{burgerservicenummer}/persoon
  * Get person information from the BRP by BSN
* GET /api/v1/profile/{burgerservicenummer}/lopende-zaken
  * Get all open zaken by BSN
