import { BaseEntity } from './../../shared';

export class Attribute implements BaseEntity {
    constructor(
        public id?: number,
        public attributeName?: string,
        public subCategoryId?: number,
    ) {
    }
}
