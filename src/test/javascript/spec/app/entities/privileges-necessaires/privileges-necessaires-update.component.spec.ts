/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AuditReportTestModule } from '../../../test.module';
import { PrivilegesNecessairesUpdateComponent } from 'app/entities/privileges-necessaires/privileges-necessaires-update.component';
import { PrivilegesNecessairesService } from 'app/entities/privileges-necessaires/privileges-necessaires.service';
import { PrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';

describe('Component Tests', () => {
    describe('PrivilegesNecessaires Management Update Component', () => {
        let comp: PrivilegesNecessairesUpdateComponent;
        let fixture: ComponentFixture<PrivilegesNecessairesUpdateComponent>;
        let service: PrivilegesNecessairesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [PrivilegesNecessairesUpdateComponent]
            })
                .overrideTemplate(PrivilegesNecessairesUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PrivilegesNecessairesUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PrivilegesNecessairesService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PrivilegesNecessaires(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.privilegesNecessaires = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PrivilegesNecessaires();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.privilegesNecessaires = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
