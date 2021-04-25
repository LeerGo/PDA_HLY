package com.arpa.and.wms.arch.binding.command;

/**
 * Represents a function with zero arguments.
 *
 * @param <T>
 *         the result type
 */
public interface BindingFunction <T> {
    T call();
}
