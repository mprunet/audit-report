import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVulnerabilite } from 'app/shared/model/vulnerabilite.model';

type EntityResponseType = HttpResponse<IVulnerabilite>;
type EntityArrayResponseType = HttpResponse<IVulnerabilite[]>;

@Injectable({ providedIn: 'root' })
export class VulnerabiliteService {
    public resourceUrl = SERVER_API_URL + 'api/vulnerabilites';

    constructor(protected http: HttpClient) {}

    create(vulnerabilite: IVulnerabilite): Observable<EntityResponseType> {
        return this.http.post<IVulnerabilite>(this.resourceUrl, vulnerabilite, { observe: 'response' });
    }

    update(vulnerabilite: IVulnerabilite): Observable<EntityResponseType> {
        return this.http.put<IVulnerabilite>(this.resourceUrl, vulnerabilite, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IVulnerabilite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IVulnerabilite[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
