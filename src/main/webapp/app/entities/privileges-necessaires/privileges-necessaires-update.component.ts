import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';
import { PrivilegesNecessairesService } from './privileges-necessaires.service';

@Component({
    selector: 'jhi-privileges-necessaires-update',
    templateUrl: './privileges-necessaires-update.component.html'
})
export class PrivilegesNecessairesUpdateComponent implements OnInit {
    privilegesNecessaires: IPrivilegesNecessaires;
    isSaving: boolean;

    constructor(protected privilegesNecessairesService: PrivilegesNecessairesService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ privilegesNecessaires }) => {
            this.privilegesNecessaires = privilegesNecessaires;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.privilegesNecessaires.id !== undefined) {
            this.subscribeToSaveResponse(this.privilegesNecessairesService.update(this.privilegesNecessaires));
        } else {
            this.subscribeToSaveResponse(this.privilegesNecessairesService.create(this.privilegesNecessaires));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrivilegesNecessaires>>) {
        result.subscribe(
            (res: HttpResponse<IPrivilegesNecessaires>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
