import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AuditReportProjetModule } from './projet/projet.module';
import { AuditReportPrivilegesNecessairesModule } from './privileges-necessaires/privileges-necessaires.module';
import { AuditReportComposantImpacteModule } from './composant-impacte/composant-impacte.module';
import { AuditReportVulnerabiliteRefModule } from './vulnerabilite-ref/vulnerabilite-ref.module';
import { AuditReportVulnerabiliteModule } from './vulnerabilite/vulnerabilite.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        AuditReportProjetModule,
        AuditReportPrivilegesNecessairesModule,
        AuditReportComposantImpacteModule,
        AuditReportVulnerabiliteRefModule,
        AuditReportVulnerabiliteModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AuditReportEntityModule {}
