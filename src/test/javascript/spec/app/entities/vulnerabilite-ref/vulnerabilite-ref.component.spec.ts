/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AuditReportTestModule } from '../../../test.module';
import { VulnerabiliteRefComponent } from 'app/entities/vulnerabilite-ref/vulnerabilite-ref.component';
import { VulnerabiliteRefService } from 'app/entities/vulnerabilite-ref/vulnerabilite-ref.service';
import { VulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';

describe('Component Tests', () => {
    describe('VulnerabiliteRef Management Component', () => {
        let comp: VulnerabiliteRefComponent;
        let fixture: ComponentFixture<VulnerabiliteRefComponent>;
        let service: VulnerabiliteRefService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [VulnerabiliteRefComponent],
                providers: []
            })
                .overrideTemplate(VulnerabiliteRefComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VulnerabiliteRefComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VulnerabiliteRefService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new VulnerabiliteRef(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.vulnerabiliteRefs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
