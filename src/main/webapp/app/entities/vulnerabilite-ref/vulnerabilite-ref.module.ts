import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuditReportSharedModule } from 'app/shared';
import {
    VulnerabiliteRefComponent,
    VulnerabiliteRefDetailComponent,
    VulnerabiliteRefUpdateComponent,
    VulnerabiliteRefDeletePopupComponent,
    VulnerabiliteRefDeleteDialogComponent,
    vulnerabiliteRefRoute,
    vulnerabiliteRefPopupRoute
} from './';

const ENTITY_STATES = [...vulnerabiliteRefRoute, ...vulnerabiliteRefPopupRoute];

@NgModule({
    imports: [AuditReportSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VulnerabiliteRefComponent,
        VulnerabiliteRefDetailComponent,
        VulnerabiliteRefUpdateComponent,
        VulnerabiliteRefDeleteDialogComponent,
        VulnerabiliteRefDeletePopupComponent
    ],
    entryComponents: [
        VulnerabiliteRefComponent,
        VulnerabiliteRefUpdateComponent,
        VulnerabiliteRefDeleteDialogComponent,
        VulnerabiliteRefDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AuditReportVulnerabiliteRefModule {}
