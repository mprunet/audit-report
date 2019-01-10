import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';
import { VulnerabiliteRefService } from './vulnerabilite-ref.service';

@Component({
    selector: 'jhi-vulnerabilite-ref-delete-dialog',
    templateUrl: './vulnerabilite-ref-delete-dialog.component.html'
})
export class VulnerabiliteRefDeleteDialogComponent {
    vulnerabiliteRef: IVulnerabiliteRef;

    constructor(
        protected vulnerabiliteRefService: VulnerabiliteRefService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vulnerabiliteRefService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vulnerabiliteRefListModification',
                content: 'Deleted an vulnerabiliteRef'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vulnerabilite-ref-delete-popup',
    template: ''
})
export class VulnerabiliteRefDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vulnerabiliteRef }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VulnerabiliteRefDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.vulnerabiliteRef = vulnerabiliteRef;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
