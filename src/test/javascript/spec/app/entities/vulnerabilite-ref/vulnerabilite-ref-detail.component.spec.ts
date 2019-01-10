/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AuditReportTestModule } from '../../../test.module';
import { VulnerabiliteRefDetailComponent } from 'app/entities/vulnerabilite-ref/vulnerabilite-ref-detail.component';
import { VulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';

describe('Component Tests', () => {
    describe('VulnerabiliteRef Management Detail Component', () => {
        let comp: VulnerabiliteRefDetailComponent;
        let fixture: ComponentFixture<VulnerabiliteRefDetailComponent>;
        const route = ({ data: of({ vulnerabiliteRef: new VulnerabiliteRef(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [VulnerabiliteRefDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VulnerabiliteRefDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VulnerabiliteRefDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vulnerabiliteRef).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
