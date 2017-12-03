import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';

import {
    CategoryService,
    CategoryPopupService,
    CategoryComponent,
    CategoryDetailComponent,
    CategoryDialogComponent,
    CategoryPopupComponent,
    CategoryDeletePopupComponent,
    CategoryDeleteDialogComponent,
    CategoryListComponent,
    categoryRoute,
    categoryPopupRoute,
} from './';
import { EntitySharedModule } from '../entity-shared.module';

const ENTITY_STATES = [
    ...categoryRoute,
    ...categoryPopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EntitySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CategoryComponent,
        CategoryDetailComponent,
        CategoryDialogComponent,
        CategoryDeleteDialogComponent,
        CategoryPopupComponent,
        CategoryDeletePopupComponent,
        CategoryListComponent
    ],
    entryComponents: [
        CategoryComponent,
        CategoryDetailComponent,
        CategoryDialogComponent,
        CategoryPopupComponent,
        CategoryDeleteDialogComponent,
        CategoryDeletePopupComponent,
        CategoryListComponent
    ],
    providers: [
        CategoryService,
        CategoryPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        CategoryListComponent
    ]
})
export class EauctionCategoryModule {}
