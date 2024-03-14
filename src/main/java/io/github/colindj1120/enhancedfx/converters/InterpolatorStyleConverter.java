/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.converters;

import javafx.animation.Interpolator;
import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Optional;

/**
 * A specialized {@link StyleConverter} that converts CSS string representations into {@link Interpolator} objects for JavaFX. This converter facilitates the use of both predefined and custom interpolators
 * directly from CSS, enabling sophisticated animation effects within JavaFX applications.
 *
 * <p><strong>Supported Interpolators:</strong></p>
 * <ul>
 *     <li><em>Predefined Interpolators</em>: EASE_BOTH, EASE_IN, EASE_OUT, LINEAR</li>
 *     <li><em>Spline Interpolator</em>: Defined by four control points as SPLINE(mX1, mY1, mX2, mY2).</li>
 *     <li><em>Tangent Interpolator (2 Parameters)</em>: Defined by initial rate and duration as TANGENT(duration, rate).</li>
 *     <li><em>Tangent Interpolator (4 Parameters)</em>: Defined by in and out tangents as TANGENT(inDuration, inRate, outDuration, outRate).</li>
 *     <li>Note: Tangent Duration is in millis</li>
 * </ul>
 *
 * <p><strong>Usage:</strong> To use this converter, define CSS properties with the appropriate syntax for the desired interpolator. This class parses the string representations and instantiates
 * the corresponding {@link Interpolator} objects.</p>
 *
 * <p><strong>Examples:</strong></p>
 * <pre>
 * .ease-animation {
 *     -fx-interpolator: "EASE_BOTH";
 * }
 *
 * .spline-animation {
 *     -fx-interpolator: "SPLINE(0.25, 0.1, 0.75, 0.9)";
 * }
 *
 * .tangent-animation {
 *     -fx-interpolator: "TANGENT(500, 1.5)";
 * }
 *
 * .complex-tangent-animation {
 *     -fx-interpolator: "TANGENT(300, 1.2, 500, 0.8)";
 * }
 * </pre>
 *
 * <p>These CSS snippets can be applied to JavaFX nodes to specify the timing and acceleration/deceleration patterns of animations, enabling rich and dynamic user interface behaviors.</p>
 *
 * <p>This converter is implemented as a singleton to ensure consistent and efficient usage across an application. Use {@link InterpolatorStyleConverter#getInstance()} to access the converter.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Interpolator
 * @see StyleConverter
 */
public class InterpolatorStyleConverter extends StyleConverter<String, Interpolator> {
    // Singleton instance
    private static class Holder {
        static final InterpolatorStyleConverter INSTANCE = new InterpolatorStyleConverter();
    }

    /**
     * Gets the singleton instance of InterpolatorStyleConverter.
     *
     * @return The singleton instance of InterpolatorStyleConverter.
     */
    public static InterpolatorStyleConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor to ensure a single instance of the {@code InterpolatorStyleConverter}.
     *
     * <p>
     * This constructor is part of the singleton design pattern implementation for the {@code InterpolatorStyleConverter}. It prevents direct instantiation from outside the class, ensuring that only one
     * instance of {@code InterpolatorStyleConverter} is created and accessible globally through a static method provided by the class. This approach is used to manage a central point of access to the converter
     * functionalities while ensuring that the overhead of multiple instances is avoided.
     * </p>
     *
     * <p>
     * Use {@link InterpolatorStyleConverter#getInstance()} to access the single instance of this class.
     * </p>
     */
    private InterpolatorStyleConverter() {}

    /**
     * Converts a CSS string value to an {@link Interpolator}.
     *
     * @param value
     *         The {@link ParsedValue} containing the CSS string to convert.
     * @param notUsed
     *         Not used in this context.
     *
     * @return The {@link Interpolator} corresponding to the CSS string value.
     */
    @Override
    public Interpolator convert(ParsedValue<String, Interpolator> value, Font notUsed) {
        return Optional.ofNullable(value.getValue())
                       .map(String::trim)
                       .map(String::toUpperCase)
                       .flatMap(this::determineInterpolator)
                       .orElse(Interpolator.LINEAR);
    }

    /**
     * Determines the type of interpolator based on the provided string representation. This method checks the prefix of the string to decide which parsing method to call.
     *
     * @param string
     *         The string representation of the interpolator to be parsed.
     *
     * @return An {@link Optional} containing the {@link Interpolator} if successfully parsed; otherwise, an empty {@link Optional}.
     */
    private Optional<Interpolator> determineInterpolator(String string) {
        if (string.startsWith("SPLINE")) {
            return parseSpline(string);
        } else if (string.startsWith("TANGENT")) {
            return parseTangent(string);
        } else {
            return parsePredefinedInterpolator(string);
        }
    }

    /**
     * Parses a SPLINE interpolator from a string representation. The SPLINE interpolator is defined by four control points (mX1, mY1, mX2, mY2).
     *
     * <pre>
     * Example usage in CSS:
     *      -fx-interpolator: "SPLINE(0.25, 0.1, 0.75, 0.9)";
     * </pre>
     *
     * @param string
     *         The string representation of the SPLINE interpolator.
     *
     * @return An {@link Optional} containing the {@link Interpolator} spline if successfully parsed; otherwise, an empty {@link Optional}.
     */
    private Optional<Interpolator> parseSpline(String string) {
        return parseParameters(string, "SPLINE", 4)
                .map(params -> Interpolator.SPLINE(params[0], params[1], params[2], params[3]));
    }

    /**
     * Parses a TANGENT interpolator from a string representation. Supports both two-parameter (duration, rate) and four-parameter (inDuration, inRate, outDuration, outRate) formats.
     *
     * <pre>
     * Example usage in CSS:
     *      -fx-interpolator: "TANGENT(500, 1.5)";
     *      -fx-interpolator: "TANGENT(200, 1.0, 300, 1.5)";
     * </pre>
     *
     * @param string
     *         The string representation of the TANGENT interpolator.
     *
     * @return An {@link Optional} containing the {@link Interpolator} tangent if successfully parsed; otherwise, an empty {@link Optional}.
     */
    private Optional<Interpolator> parseTangent(String string) {
        return parseParameters(string, "TANGENT", 2)
                .map(params -> Interpolator.TANGENT(Duration.millis(params[0]), params[1]))
                .or(() -> parseParameters(string, "TANGENT", 4)
                        .map(params -> Interpolator.TANGENT(Duration.millis(params[0]), params[1], Duration.millis(params[2]), params[3])));
    }

    /**
     * Parses predefined interpolators (EASE_BOTH, EASE_IN, EASE_OUT, LINEAR) from a string representation.
     *
     * <pre>
     * Example usage in CSS:
     *      -fx-interpolator: "EASE_BOTH";
     * </pre>
     *
     * @param string
     *         The string representation of the predefined interpolator.
     *
     * @return An {@link Optional} containing the predefined {@link Interpolator} if successfully parsed; otherwise, an empty {@link Optional}.
     */
    private Optional<Interpolator> parsePredefinedInterpolator(String string) {
        return switch (string) {
            case "EASE_BOTH" -> Optional.of(Interpolator.EASE_BOTH);
            case "EASE_IN" -> Optional.of(Interpolator.EASE_IN);
            case "EASE_OUT" -> Optional.of(Interpolator.EASE_OUT);
            case "LINEAR" -> Optional.of(Interpolator.LINEAR);
            default -> Optional.empty();
        };
    }

    /**
     * Parses parameters from a string representation intended for interpolator definitions. This method extracts numeric parameters from a substring and converts them into a double array.
     *
     * @param string
     *         The full string representation containing the interpolator parameters.
     * @param prefix
     *         The prefix that identifies the type of interpolator (e.g., "SPLINE", "TANGENT").
     * @param expectedParams
     *         The expected number of numeric parameters for the interpolator.
     *
     * @return An {@link Optional} containing an array of doubles if successfully parsed; otherwise, an empty {@link Optional}.
     */
    private Optional<double[]> parseParameters(String string, String prefix, int expectedParams) {
        try {
            return Optional.of(string)
                           .map(s -> s.substring(prefix.length() + 1, s.length() - 1))
                           .map(s -> s.split(","))
                           .filter(parts -> parts.length == expectedParams)
                           .map(Arrays::stream)
                           .map(stream -> stream.map(String::trim)
                                                .mapToDouble(Double::parseDouble)
                                                .toArray());
        }
        catch (NumberFormatException e) {
            System.err.printf("Failed to parse parameters for %s: %s%n", prefix, e.getMessage());
        }
        return Optional.empty();
    }
}