/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AuditReportTestModule } from '../../../test.module';
import { ComposantImpacteComponent } from 'app/entities/composant-impacte/composant-impacte.component';
import { ComposantImpacteService } from 'app/entities/composant-impacte/composant-impacte.service';
import { ComposantImpacte } from 'app/shared/model/composant-impacte.model';

describe('Component Tests', () => {
    describe('ComposantImpacte Management Component', () => {
        let comp: ComposantImpacteComponent;
        let fixture: ComponentFixture<ComposantImpacteComponent>;
        let service: ComposantImpacteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [ComposantImpacteComponent],
                providers: []
            })
                .overrideTemplate(ComposantImpacteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComposantImpacteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComposantImpacteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ComposantImpacte(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.composantImpactes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
