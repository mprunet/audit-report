import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVulnerabilite } from 'app/shared/model/vulnerabilite.model';
import { VulnerabiliteService } from './vulnerabilite.service';

@Component({
    selector: 'jhi-vulnerabilite-delete-dialog',
    templateUrl: './vulnerabilite-delete-dialog.component.html'
})
export class VulnerabiliteDeleteDialogComponent {
    vulnerabilite: IVulnerabilite;

    constructor(
        protected vulnerabiliteService: VulnerabiliteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vulnerabiliteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vulnerabiliteListModification',
                content: 'Deleted an vulnerabilite'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vulnerabilite-delete-popup',
    template: ''
})
export class VulnerabiliteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vulnerabilite }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VulnerabiliteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.vulnerabilite = vulnerabilite;
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
