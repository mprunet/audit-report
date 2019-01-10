/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AuditReportTestModule } from '../../../test.module';
import { VulnerabiliteRefUpdateComponent } from 'app/entities/vulnerabilite-ref/vulnerabilite-ref-update.component';
import { VulnerabiliteRefService } from 'app/entities/vulnerabilite-ref/vulnerabilite-ref.service';
import { VulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';

describe('Component Tests', () => {
    describe('VulnerabiliteRef Management Update Component', () => {
        let comp: VulnerabiliteRefUpdateComponent;
        let fixture: ComponentFixture<VulnerabiliteRefUpdateComponent>;
        let service: VulnerabiliteRefService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [VulnerabiliteRefUpdateComponent]
            })
                .overrideTemplate(VulnerabiliteRefUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VulnerabiliteRefUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VulnerabiliteRefService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new VulnerabiliteRef(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.vulnerabiliteRef = entity;
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
                    const entity = new VulnerabiliteRef();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.vulnerabiliteRef = entity;
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
