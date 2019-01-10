import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IVulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';
import { VulnerabiliteRefService } from './vulnerabilite-ref.service';
import { IVulnerabilite } from 'app/shared/model/vulnerabilite.model';
import { VulnerabiliteService } from 'app/entities/vulnerabilite';

@Component({
    selector: 'jhi-vulnerabilite-ref-update',
    templateUrl: './vulnerabilite-ref-update.component.html'
})
export class VulnerabiliteRefUpdateComponent implements OnInit {
    vulnerabiliteRef: IVulnerabiliteRef;
    isSaving: boolean;

    vulnerabilites: IVulnerabilite[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected vulnerabiliteRefService: VulnerabiliteRefService,
        protected vulnerabiliteService: VulnerabiliteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vulnerabiliteRef }) => {
            this.vulnerabiliteRef = vulnerabiliteRef;
        });
        this.vulnerabiliteService.query().subscribe(
            (res: HttpResponse<IVulnerabilite[]>) => {
                this.vulnerabilites = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.vulnerabiliteRef.id !== undefined) {
            this.subscribeToSaveResponse(this.vulnerabiliteRefService.update(this.vulnerabiliteRef));
        } else {
            this.subscribeToSaveResponse(this.vulnerabiliteRefService.create(this.vulnerabiliteRef));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVulnerabiliteRef>>) {
        result.subscribe((res: HttpResponse<IVulnerabiliteRef>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackVulnerabiliteById(index: number, item: IVulnerabilite) {
        return item.id;
    }
}
