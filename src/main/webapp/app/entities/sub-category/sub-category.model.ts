import { BaseEntity } from './../../shared';

export class SubCategory implements BaseEntity {
    constructor(
        public id?: number,
        public subCategoryName?: string,
        public attributes?: BaseEntity[],
        public categoryId?: number,
    ) {
    }
}
