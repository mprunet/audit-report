import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComposantImpacte } from 'app/shared/model/composant-impacte.model';
import { ComposantImpacteService } from './composant-impacte.service';

@Component({
    selector: 'jhi-composant-impacte-delete-dialog',
    templateUrl: './composant-impacte-delete-dialog.component.html'
})
export class ComposantImpacteDeleteDialogComponent {
    composantImpacte: IComposantImpacte;

    constructor(
        protected composantImpacteService: ComposantImpacteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.composantImpacteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'composantImpacteListModification',
                content: 'Deleted an composantImpacte'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-composant-impacte-delete-popup',
    template: ''
})
export class ComposantImpacteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ composantImpacte }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ComposantImpacteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.composantImpacte = composantImpacte;
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
