import { Component, OnInit, Input } from '@angular/core';

import { SubCategory } from '../sub-category.model';
import { SubCategoryService } from '../sub-category.service';

@Component({
    selector: 'app-sub-category-label',
    templateUrl: './sub-category-label.component.html'
})
export class SubCategoryLabelComponent implements OnInit {
    @Input()
    id: number;
    subCategory: SubCategory;

    constructor(
        private subCategoryService: SubCategoryService
    ) { }

    ngOnInit() {
        this.load(this.id);
    }

    load(id) {
        this.subCategoryService.find(id).subscribe((subCategory) => {
            this.subCategory = subCategory;
        });
    }
    previousState() {
        window.history.back();
    }
}
