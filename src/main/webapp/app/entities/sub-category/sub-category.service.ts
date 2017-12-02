import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { SubCategory } from './sub-category.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SubCategoryService {

    private resourceUrl = SERVER_API_URL + 'api/sub-categories';

    constructor(private http: Http) { }

    create(subCategory: SubCategory): Observable<SubCategory> {
        const copy = this.convert(subCategory);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(subCategory: SubCategory): Observable<SubCategory> {
        const copy = this.convert(subCategory);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<SubCategory> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to SubCategory.
     */
    private convertItemFromServer(json: any): SubCategory {
        const entity: SubCategory = Object.assign(new SubCategory(), json);
        return entity;
    }

    /**
     * Convert a SubCategory to a JSON which can be sent to the server.
     */
    private convert(subCategory: SubCategory): SubCategory {
        const copy: SubCategory = Object.assign({}, subCategory);
        return copy;
    }
}
