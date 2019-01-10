import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuditReportSharedModule } from 'app/shared';
import {
    PrivilegesNecessairesComponent,
    PrivilegesNecessairesDetailComponent,
    PrivilegesNecessairesUpdateComponent,
    PrivilegesNecessairesDeletePopupComponent,
    PrivilegesNecessairesDeleteDialogComponent,
    privilegesNecessairesRoute,
    privilegesNecessairesPopupRoute
} from './';

const ENTITY_STATES = [...privilegesNecessairesRoute, ...privilegesNecessairesPopupRoute];

@NgModule({
    imports: [AuditReportSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PrivilegesNecessairesComponent,
        PrivilegesNecessairesDetailComponent,
        PrivilegesNecessairesUpdateComponent,
        PrivilegesNecessairesDeleteDialogComponent,
        PrivilegesNecessairesDeletePopupComponent
    ],
    entryComponents: [
        PrivilegesNecessairesComponent,
        PrivilegesNecessairesUpdateComponent,
        PrivilegesNecessairesDeleteDialogComponent,
        PrivilegesNecessairesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AuditReportPrivilegesNecessairesModule {}
