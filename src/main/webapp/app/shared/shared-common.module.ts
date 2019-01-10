import { NgModule } from '@angular/core';

import { AuditReportSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [AuditReportSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [AuditReportSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class AuditReportSharedCommonModule {}
