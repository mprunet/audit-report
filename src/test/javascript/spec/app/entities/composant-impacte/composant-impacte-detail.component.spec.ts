/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AuditReportTestModule } from '../../../test.module';
import { ComposantImpacteDetailComponent } from 'app/entities/composant-impacte/composant-impacte-detail.component';
import { ComposantImpacte } from 'app/shared/model/composant-impacte.model';

describe('Component Tests', () => {
    describe('ComposantImpacte Management Detail Component', () => {
        let comp: ComposantImpacteDetailComponent;
        let fixture: ComponentFixture<ComposantImpacteDetailComponent>;
        const route = ({ data: of({ composantImpacte: new ComposantImpacte(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [ComposantImpacteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ComposantImpacteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComposantImpacteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.composantImpacte).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
