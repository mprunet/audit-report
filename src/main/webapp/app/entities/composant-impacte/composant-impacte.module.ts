import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuditReportSharedModule } from 'app/shared';
import {
    ComposantImpacteComponent,
    ComposantImpacteDetailComponent,
    ComposantImpacteUpdateComponent,
    ComposantImpacteDeletePopupComponent,
    ComposantImpacteDeleteDialogComponent,
    composantImpacteRoute,
    composantImpactePopupRoute
} from './';

const ENTITY_STATES = [...composantImpacteRoute, ...composantImpactePopupRoute];

@NgModule({
    imports: [AuditReportSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ComposantImpacteComponent,
        ComposantImpacteDetailComponent,
        ComposantImpacteUpdateComponent,
        ComposantImpacteDeleteDialogComponent,
        ComposantImpacteDeletePopupComponent
    ],
    entryComponents: [
        ComposantImpacteComponent,
        ComposantImpacteUpdateComponent,
        ComposantImpacteDeleteDialogComponent,
        ComposantImpacteDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AuditReportComposantImpacteModule {}
