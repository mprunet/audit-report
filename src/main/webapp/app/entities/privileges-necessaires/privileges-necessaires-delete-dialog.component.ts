import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';
import { PrivilegesNecessairesService } from './privileges-necessaires.service';

@Component({
    selector: 'jhi-privileges-necessaires-delete-dialog',
    templateUrl: './privileges-necessaires-delete-dialog.component.html'
})
export class PrivilegesNecessairesDeleteDialogComponent {
    privilegesNecessaires: IPrivilegesNecessaires;

    constructor(
        protected privilegesNecessairesService: PrivilegesNecessairesService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.privilegesNecessairesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'privilegesNecessairesListModification',
                content: 'Deleted an privilegesNecessaires'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-privileges-necessaires-delete-popup',
    template: ''
})
export class PrivilegesNecessairesDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ privilegesNecessaires }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PrivilegesNecessairesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.privilegesNecessaires = privilegesNecessaires;
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
