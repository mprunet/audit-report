/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AuditReportTestModule } from '../../../test.module';
import { PrivilegesNecessairesDetailComponent } from 'app/entities/privileges-necessaires/privileges-necessaires-detail.component';
import { PrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';

describe('Component Tests', () => {
    describe('PrivilegesNecessaires Management Detail Component', () => {
        let comp: PrivilegesNecessairesDetailComponent;
        let fixture: ComponentFixture<PrivilegesNecessairesDetailComponent>;
        const route = ({ data: of({ privilegesNecessaires: new PrivilegesNecessaires(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [PrivilegesNecessairesDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PrivilegesNecessairesDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PrivilegesNecessairesDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.privilegesNecessaires).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
