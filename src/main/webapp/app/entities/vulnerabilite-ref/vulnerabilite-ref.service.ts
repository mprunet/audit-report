import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';

type EntityResponseType = HttpResponse<IVulnerabiliteRef>;
type EntityArrayResponseType = HttpResponse<IVulnerabiliteRef[]>;

@Injectable({ providedIn: 'root' })
export class VulnerabiliteRefService {
    public resourceUrl = SERVER_API_URL + 'api/vulnerabilite-refs';

    constructor(protected http: HttpClient) {}

    create(vulnerabiliteRef: IVulnerabiliteRef): Observable<EntityResponseType> {
        return this.http.post<IVulnerabiliteRef>(this.resourceUrl, vulnerabiliteRef, { observe: 'response' });
    }

    update(vulnerabiliteRef: IVulnerabiliteRef): Observable<EntityResponseType> {
        return this.http.put<IVulnerabiliteRef>(this.resourceUrl, vulnerabiliteRef, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IVulnerabiliteRef>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IVulnerabiliteRef[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
