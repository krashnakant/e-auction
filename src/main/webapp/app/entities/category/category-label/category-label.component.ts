import { Component, OnInit, Input } from '@angular/core';

import { Category } from '../category.model';
import { CategoryService } from '../category.service';

@Component({
    selector: 'app-category-label',
    templateUrl: './category-label.component.html'
})
export class CategoryLabelComponent implements OnInit {
    @Input()
    id: number;
    category: Category;

    constructor(
        private categoryService: CategoryService
    ) { }

    ngOnInit() {
        this.load(this.id);
    }

    load(id) {
        this.categoryService.find(id).subscribe((category) => {
            this.category = category;
        });
    }
    previousState() {
        window.history.back();
    }
}
