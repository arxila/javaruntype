// $ANTLR 3.5.3 io/arxila/javaruntype/type/parser/Type.g 2025-08-31 03:20:30

/*
 * =========================================================================
 *
 *   Copyright (c) 2009-2025 Arxila OSS (https://arxila.io)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *   implied. See the License for the specific language governing
 *   permissions and limitations under the License.
 *
 * =========================================================================
 */
package io.arxila.javaruntype.type.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TypeLexer extends Lexer {
	public static final int EOF=-1;
	public static final int ARRAY=4;
	public static final int BEGINTYPEPARAM=5;
	public static final int CLASSNAME=6;
	public static final int COMMA=7;
	public static final int ENDTYPEPARAM=8;
	public static final int EXT=9;
	public static final int EXTENDS=10;
	public static final int SUP=11;
	public static final int SUPER=12;
	public static final int UNKNOWN=13;
	public static final int WS=14;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public TypeLexer() {} 
	public TypeLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public TypeLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "io/arxila/javaruntype/type/parser/Type.g"; }

	// $ANTLR start "ARRAY"
	public final void mARRAY() throws RecognitionException {
		try {
			int _type = ARRAY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:30:7: ( '[]' )
			// io/arxila/javaruntype/type/parser/Type.g:30:9: '[]'
			{
			match("[]"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARRAY"

	// $ANTLR start "BEGINTYPEPARAM"
	public final void mBEGINTYPEPARAM() throws RecognitionException {
		try {
			int _type = BEGINTYPEPARAM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:31:16: ( '<' )
			// io/arxila/javaruntype/type/parser/Type.g:31:18: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BEGINTYPEPARAM"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:32:7: ( ',' )
			// io/arxila/javaruntype/type/parser/Type.g:32:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "ENDTYPEPARAM"
	public final void mENDTYPEPARAM() throws RecognitionException {
		try {
			int _type = ENDTYPEPARAM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:33:14: ( '>' )
			// io/arxila/javaruntype/type/parser/Type.g:33:16: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ENDTYPEPARAM"

	// $ANTLR start "EXT"
	public final void mEXT() throws RecognitionException {
		try {
			int _type = EXT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:34:5: ( 'EXT' )
			// io/arxila/javaruntype/type/parser/Type.g:34:7: 'EXT'
			{
			match("EXT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXT"

	// $ANTLR start "EXTENDS"
	public final void mEXTENDS() throws RecognitionException {
		try {
			int _type = EXTENDS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:35:9: ( ' extends ' )
			// io/arxila/javaruntype/type/parser/Type.g:35:11: ' extends '
			{
			match(" extends "); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXTENDS"

	// $ANTLR start "SUP"
	public final void mSUP() throws RecognitionException {
		try {
			int _type = SUP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:36:5: ( 'SUP' )
			// io/arxila/javaruntype/type/parser/Type.g:36:7: 'SUP'
			{
			match("SUP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUP"

	// $ANTLR start "SUPER"
	public final void mSUPER() throws RecognitionException {
		try {
			int _type = SUPER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:37:7: ( ' super ' )
			// io/arxila/javaruntype/type/parser/Type.g:37:9: ' super '
			{
			match(" super "); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUPER"

	// $ANTLR start "UNKNOWN"
	public final void mUNKNOWN() throws RecognitionException {
		try {
			int _type = UNKNOWN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:38:9: ( '?' )
			// io/arxila/javaruntype/type/parser/Type.g:38:11: '?'
			{
			match('?'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNKNOWN"

	// $ANTLR start "CLASSNAME"
	public final void mCLASSNAME() throws RecognitionException {
		try {
			int _type = CLASSNAME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:120:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '.' | '$' )+ )
			// io/arxila/javaruntype/type/parser/Type.g:120:7: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '.' | '$' )+
			{
			// io/arxila/javaruntype/type/parser/Type.g:120:7: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '.' | '$' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0=='$'||(LA1_0 >= '-' && LA1_0 <= '.')||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// io/arxila/javaruntype/type/parser/Type.g:
					{
					if ( input.LA(1)=='$'||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLASSNAME"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// io/arxila/javaruntype/type/parser/Type.g:124:5: ( ( ' ' )+ )
			// io/arxila/javaruntype/type/parser/Type.g:124:7: ( ' ' )+
			{
			// io/arxila/javaruntype/type/parser/Type.g:124:7: ( ' ' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==' ') ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// io/arxila/javaruntype/type/parser/Type.g:124:8: ' '
					{
					match(' '); 
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// io/arxila/javaruntype/type/parser/Type.g:1:8: ( ARRAY | BEGINTYPEPARAM | COMMA | ENDTYPEPARAM | EXT | EXTENDS | SUP | SUPER | UNKNOWN | CLASSNAME | WS )
		int alt3=11;
		switch ( input.LA(1) ) {
		case '[':
			{
			alt3=1;
			}
			break;
		case '<':
			{
			alt3=2;
			}
			break;
		case ',':
			{
			alt3=3;
			}
			break;
		case '>':
			{
			alt3=4;
			}
			break;
		case 'E':
			{
			int LA3_5 = input.LA(2);
			if ( (LA3_5=='X') ) {
				int LA3_10 = input.LA(3);
				if ( (LA3_10=='T') ) {
					int LA3_15 = input.LA(4);
					if ( (LA3_15=='$'||(LA3_15 >= '-' && LA3_15 <= '.')||(LA3_15 >= '0' && LA3_15 <= '9')||(LA3_15 >= 'A' && LA3_15 <= 'Z')||LA3_15=='_'||(LA3_15 >= 'a' && LA3_15 <= 'z')) ) {
						alt3=10;
					}

					else {
						alt3=5;
					}

				}

				else {
					alt3=10;
				}

			}

			else {
				alt3=10;
			}

			}
			break;
		case ' ':
			{
			switch ( input.LA(2) ) {
			case 'e':
				{
				alt3=6;
				}
				break;
			case 's':
				{
				alt3=8;
				}
				break;
			default:
				alt3=11;
			}
			}
			break;
		case 'S':
			{
			int LA3_7 = input.LA(2);
			if ( (LA3_7=='U') ) {
				int LA3_14 = input.LA(3);
				if ( (LA3_14=='P') ) {
					int LA3_16 = input.LA(4);
					if ( (LA3_16=='$'||(LA3_16 >= '-' && LA3_16 <= '.')||(LA3_16 >= '0' && LA3_16 <= '9')||(LA3_16 >= 'A' && LA3_16 <= 'Z')||LA3_16=='_'||(LA3_16 >= 'a' && LA3_16 <= 'z')) ) {
						alt3=10;
					}

					else {
						alt3=7;
					}

				}

				else {
					alt3=10;
				}

			}

			else {
				alt3=10;
			}

			}
			break;
		case '?':
			{
			alt3=9;
			}
			break;
		case '$':
		case '-':
		case '.':
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case '_':
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			{
			alt3=10;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 3, 0, input);
			throw nvae;
		}
		switch (alt3) {
			case 1 :
				// io/arxila/javaruntype/type/parser/Type.g:1:10: ARRAY
				{
				mARRAY(); 

				}
				break;
			case 2 :
				// io/arxila/javaruntype/type/parser/Type.g:1:16: BEGINTYPEPARAM
				{
				mBEGINTYPEPARAM(); 

				}
				break;
			case 3 :
				// io/arxila/javaruntype/type/parser/Type.g:1:31: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 4 :
				// io/arxila/javaruntype/type/parser/Type.g:1:37: ENDTYPEPARAM
				{
				mENDTYPEPARAM(); 

				}
				break;
			case 5 :
				// io/arxila/javaruntype/type/parser/Type.g:1:50: EXT
				{
				mEXT(); 

				}
				break;
			case 6 :
				// io/arxila/javaruntype/type/parser/Type.g:1:54: EXTENDS
				{
				mEXTENDS(); 

				}
				break;
			case 7 :
				// io/arxila/javaruntype/type/parser/Type.g:1:62: SUP
				{
				mSUP(); 

				}
				break;
			case 8 :
				// io/arxila/javaruntype/type/parser/Type.g:1:66: SUPER
				{
				mSUPER(); 

				}
				break;
			case 9 :
				// io/arxila/javaruntype/type/parser/Type.g:1:72: UNKNOWN
				{
				mUNKNOWN(); 

				}
				break;
			case 10 :
				// io/arxila/javaruntype/type/parser/Type.g:1:80: CLASSNAME
				{
				mCLASSNAME(); 

				}
				break;
			case 11 :
				// io/arxila/javaruntype/type/parser/Type.g:1:90: WS
				{
				mWS(); 

				}
				break;

		}
	}



}
