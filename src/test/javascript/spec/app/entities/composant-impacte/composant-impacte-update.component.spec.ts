/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AuditReportTestModule } from '../../../test.module';
import { ComposantImpacteUpdateComponent } from 'app/entities/composant-impacte/composant-impacte-update.component';
import { ComposantImpacteService } from 'app/entities/composant-impacte/composant-impacte.service';
import { ComposantImpacte } from 'app/shared/model/composant-impacte.model';

describe('Component Tests', () => {
    describe('ComposantImpacte Management Update Component', () => {
        let comp: ComposantImpacteUpdateComponent;
        let fixture: ComponentFixture<ComposantImpacteUpdateComponent>;
        let service: ComposantImpacteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [ComposantImpacteUpdateComponent]
            })
                .overrideTemplate(ComposantImpacteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComposantImpacteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComposantImpacteService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ComposantImpacte(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.composantImpacte = entity;
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
                    const entity = new ComposantImpacte();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.composantImpacte = entity;
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
