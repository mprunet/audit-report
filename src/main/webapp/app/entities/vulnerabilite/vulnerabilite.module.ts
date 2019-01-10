import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuditReportSharedModule } from 'app/shared';
import {
    VulnerabiliteComponent,
    VulnerabiliteDetailComponent,
    VulnerabiliteUpdateComponent,
    VulnerabiliteDeletePopupComponent,
    VulnerabiliteDeleteDialogComponent,
    vulnerabiliteRoute,
    vulnerabilitePopupRoute
} from './';

const ENTITY_STATES = [...vulnerabiliteRoute, ...vulnerabilitePopupRoute];

@NgModule({
    imports: [AuditReportSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VulnerabiliteComponent,
        VulnerabiliteDetailComponent,
        VulnerabiliteUpdateComponent,
        VulnerabiliteDeleteDialogComponent,
        VulnerabiliteDeletePopupComponent
    ],
    entryComponents: [
        VulnerabiliteComponent,
        VulnerabiliteUpdateComponent,
        VulnerabiliteDeleteDialogComponent,
        VulnerabiliteDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AuditReportVulnerabiliteModule {}
