/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AuditReportTestModule } from '../../../test.module';
import { PrivilegesNecessairesDeleteDialogComponent } from 'app/entities/privileges-necessaires/privileges-necessaires-delete-dialog.component';
import { PrivilegesNecessairesService } from 'app/entities/privileges-necessaires/privileges-necessaires.service';

describe('Component Tests', () => {
    describe('PrivilegesNecessaires Management Delete Component', () => {
        let comp: PrivilegesNecessairesDeleteDialogComponent;
        let fixture: ComponentFixture<PrivilegesNecessairesDeleteDialogComponent>;
        let service: PrivilegesNecessairesService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AuditReportTestModule],
                declarations: [PrivilegesNecessairesDeleteDialogComponent]
            })
                .overrideTemplate(PrivilegesNecessairesDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PrivilegesNecessairesDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PrivilegesNecessairesService);
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
