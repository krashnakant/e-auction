import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiAlertService } from 'ng-jhipster';

import { Category } from '../category.model';
import { CategoryService } from '../category.service';
import { ResponseWrapper } from '../../../shared';

@Component({
    selector: 'app-category-list',
    templateUrl: 'category-list.component.html'
})

export class CategoryListComponent implements OnInit {
    categories: Category[];

    constructor(
        private categoryService: CategoryService,
        private jhiAlertService: JhiAlertService
    ) {
    }

    loadAll() {
        this.categoryService.query().subscribe(
            (res: ResponseWrapper) => {
                this.categories = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
    }

    trackId(index: number, item: Category) {
        return item.id;
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
