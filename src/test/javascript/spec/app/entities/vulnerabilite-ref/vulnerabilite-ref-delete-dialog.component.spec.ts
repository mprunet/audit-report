/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AuditReportTestModule } from '../../../test.module';
import { VulnerabiliteRefDeleteDialogComponent } from 'app/entities/vulnerabilite-ref/vulnerabilite-ref-delete-dialog.component';
import { VulnerabiliteRefService } from 'app/entities/vulnerabilite-ref/vulnerabilite-ref.service';

describe('Component Tests', () => {
    describe('VulnerabiliteRef Management Delete Component', () => {
        let comp: VulnerabiliteRefDeleteDialogComponent;
        let fixture: ComponentFixture<VulnerabiliteRefDeleteDialogComponent>;
        let service: VulnerabiliteRefService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [VulnerabiliteRefDeleteDialogComponent]
            })
                .overrideTemplate(VulnerabiliteRefDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VulnerabiliteRefDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VulnerabiliteRefService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
