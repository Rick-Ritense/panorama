#!/bin/bash

# If you want to persist the data you made locally you can run this script.
# The end result will update the file /imports/open-zaak/database/1-setup-zaaktype.sql

pg_dump --inserts --data-only --host localhost --port 8091 --username openzaak openzaak \
-t accounts_user \
-t catalogi_catalogus \
-t authorizations_applicatie \
-t vng_api_common_jwtsecret \
-t catalogi_zaaktype \
-t catalogi_statustype \
-t catalogi_eigenschapspecificatie \
-t catalogi_eigenschap \
-t catalogi_roltype \
-t catalogi_informatieobjecttype \
-t catalogi_zaaktypeinformatieobjecttype \
-t zgw_consumers_service \
-t zaken_zaakidentificatie \
-t zaken_zaak \
-t zaken_rol \
-t zaken_status \
-t zaken_natuurlijkpersoon > ../database/1-setup-zaaktype.sql


