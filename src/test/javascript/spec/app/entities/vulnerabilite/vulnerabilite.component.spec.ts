/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AuditReportTestModule } from '../../../test.module';
import { VulnerabiliteComponent } from 'app/entities/vulnerabilite/vulnerabilite.component';
import { VulnerabiliteService } from 'app/entities/vulnerabilite/vulnerabilite.service';
import { Vulnerabilite } from 'app/shared/model/vulnerabilite.model';

describe('Component Tests', () => {
    describe('Vulnerabilite Management Component', () => {
        let comp: VulnerabiliteComponent;
        let fixture: ComponentFixture<VulnerabiliteComponent>;
        let service: VulnerabiliteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [VulnerabiliteComponent],
                providers: []
            })
                .overrideTemplate(VulnerabiliteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VulnerabiliteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VulnerabiliteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Vulnerabilite(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.vulnerabilites[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
