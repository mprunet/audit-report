import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IProjet } from 'app/shared/model/projet.model';
import { ProjetService } from './projet.service';

@Component({
    selector: 'jhi-projet-update',
    templateUrl: './projet-update.component.html'
})
export class ProjetUpdateComponent implements OnInit {
    projet: IProjet;
    isSaving: boolean;
    dateRestitutionDp: any;

    constructor(protected projetService: ProjetService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ projet }) => {
            this.projet = projet;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.projet.id !== undefined) {
            this.subscribeToSaveResponse(this.projetService.update(this.projet));
        } else {
            this.subscribeToSaveResponse(this.projetService.create(this.projet));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjet>>) {
        result.subscribe((res: HttpResponse<IProjet>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
