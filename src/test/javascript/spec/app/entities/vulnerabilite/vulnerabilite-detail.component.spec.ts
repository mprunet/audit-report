/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AuditReportTestModule } from '../../../test.module';
import { VulnerabiliteDetailComponent } from 'app/entities/vulnerabilite/vulnerabilite-detail.component';
import { Vulnerabilite } from 'app/shared/model/vulnerabilite.model';

describe('Component Tests', () => {
    describe('Vulnerabilite Management Detail Component', () => {
        let comp: VulnerabiliteDetailComponent;
        let fixture: ComponentFixture<VulnerabiliteDetailComponent>;
        const route = ({ data: of({ vulnerabilite: new Vulnerabilite(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [VulnerabiliteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VulnerabiliteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VulnerabiliteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vulnerabilite).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
