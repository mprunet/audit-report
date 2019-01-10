/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AuditReportTestModule } from '../../../test.module';
import { PrivilegesNecessairesComponent } from 'app/entities/privileges-necessaires/privileges-necessaires.component';
import { PrivilegesNecessairesService } from 'app/entities/privileges-necessaires/privileges-necessaires.service';
import { PrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';

describe('Component Tests', () => {
    describe('PrivilegesNecessaires Management Component', () => {
        let comp: PrivilegesNecessairesComponent;
        let fixture: ComponentFixture<PrivilegesNecessairesComponent>;
        let service: PrivilegesNecessairesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [PrivilegesNecessairesComponent],
                providers: []
            })
                .overrideTemplate(PrivilegesNecessairesComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PrivilegesNecessairesComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PrivilegesNecessairesService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PrivilegesNecessaires(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.privilegesNecessaires[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
